# frozen_string_literal: true

file_path = File.expand_path('input.txt', "#{File.dirname(__FILE__)}/..")
lines = File.read(file_path).split

gamma = ''
epsilon = ''

# Loop line character count
(0..lines[0].length - 1).each do |i|
  zeros = 0
  ones  = 0

  # Loop through all lines and count ones and zeros on index i
  lines.each do |line|
    char = line[i].to_i
    case char
    when 0
      zeros += 1
    when 1
      ones += 1
    else
      break
    end
  end

  # Determines which of the two decimals is dominant/recessive and fills the gamma/epsilon with the correct value
  if zeros > ones
    gamma += '0'
    epsilon += '1'
  elsif ones > zeros
    gamma += '1'
    epsilon += '0'
  end
end

# Multiplies the decimal value of gamma and epsilon to determine the power consumption
power_consumption = gamma.to_i(2) * epsilon.to_i(2)
puts "gamma: #{gamma} (#{gamma.to_i(2)})."
puts "epsilon: #{epsilon} (#{epsilon.to_i(2)})."
puts "power_consumption = gamma*epsilon = #{power_consumption}."
