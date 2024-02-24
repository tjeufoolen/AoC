package dev.foolen.aoc2022.solutions;

import dev.foolen.aoc2022.commons.utils.IOUtil;
import lombok.Getter;
import lombok.Setter;

import java.io.FileNotFoundException;
import java.util.*;

public class Day07 extends AbstractDay {
    public Day07() {
        super(7);

        // TODO: Remove code block and test file after
        try {
            input = IOUtil.readFileAsListOfStrings("input/test-day07.txt");
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
        // END TODO: Remove code block and test file after
    }

    public static void main(String[] args) {
        new Day07().solve();
    }

    @Override
    public String solvePartOne() {
        Node rootNode = parseNodeTree();
        rootNode.print(0);
        // - X 5251801 -> too high
        // - X 638788
        // - X
        return String.valueOf(sum(rootNode));
    }

    @Override
    public String solvePartTwo() {
        return "0";
    }

    private int sum(Node node) {
        int rtn = 0;
        if (node.getSize() <= 100_000) {
            rtn += node.getSize();
//            System.out.printf("rtn += %d%n", node.getSize());
        }
        if (node instanceof DirectoryNode) {
            for (Node child : ((DirectoryNode) node).children) {
                rtn += sum(child);
//                System.out.printf("rtn += %d%n", sum(child));
            }
        }
        return rtn;
    }

    private Node parseNodeTree() {
        DirectoryNode root = new DirectoryNode("/", null);
        DirectoryNode currDir = root;

        int idx=1; // Skip first cd /
        while (idx<input.size()) {
            String[] parts = input.get(idx).split(" ");
            if (!parts[0].equals("$")) continue;

            // Handle commands
            switch (parts[1]) {
                case "cd" -> currDir = navigateDirectory(currDir, parts[2]);
                case "ls" -> {
                    idx++; // Read ls output instead of its own cmd
                    while (idx < input.size() && input.get(idx).charAt(0) != '$') {
                        parts = input.get(idx).split(" ");
                        idx++;

                        if (parts[0].equals("dir")) {
                            currDir.addChild(new DirectoryNode(parts[1], currDir));
                            continue;
                        }

                        currDir.addChild(new FileNode(parts[1], Integer.parseInt(parts[0]), currDir));
                    }
                    idx--;
                }
            }
            idx++;
        }

        return root;
    }

    private DirectoryNode navigateDirectory(DirectoryNode currDir, String targetDir) {
        if (currDir == null) return null;

        boolean isTargetParent = targetDir.equals("..");
        boolean isParentAvailable = currDir.getParent() != null;
        if (isTargetParent && isParentAvailable) {
            return currDir.getParent();
        }

        return (DirectoryNode) currDir.getChild(targetDir);
    }

    private class DirectoryNode extends Node {
        @Getter
        @Setter
        private List<Node> children;

        public DirectoryNode(String name, DirectoryNode parent) {
            super(name, parent);
            this.children = new ArrayList<>();
        }

        public void addChild(Node child) {
            if (getChild(child.getName()) != null) return;
            this.children.add(child);
        }

        public Node getChild(String name) {
            return this.children.stream()
                    .filter(c -> c.getName().equals(name))
                    .findFirst().orElse(null);
        }

        @Override
        public int getSize() {
            return this.children.stream().mapToInt(Node::getSize).sum();
        }

        @Override
        public void print(int depth) {
            System.out.printf("%s- %s (dir)%n", "\t".repeat(depth), getName());
            this.children.forEach(c -> c.print(depth + 1));
        }
    }

    private class FileNode extends Node {
        @Setter
        private int size;

        public FileNode(String name, int size, DirectoryNode parent) {
            super(name, parent);
            this.size = size;
        }

        @Override
        public int getSize() {
            return size;
        }

        @Override
        public void print(int depth) {
            System.out.printf("%s- %s (file, size=%d)%n", "\t".repeat(depth), getName(), getSize());
        }
    }

    private abstract class Node {
        @Getter
        @Setter
        private String name;

        @Getter
        @Setter
        private DirectoryNode parent;

        public Node(String name, DirectoryNode parent) {
            this.name = name;
            this.parent = parent;
        }

        public String getPath() {
            if (parent == null) return name;
            return parent.getPath() + "/" + name;
        }

        public abstract int getSize();

        public abstract void print(int depth);
    }
}
