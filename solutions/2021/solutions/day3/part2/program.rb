# frozen_string_literal: true

file_path = File.expand_path('input.txt', "#{File.dirname(__FILE__)}/..")
lines = File.read(file_path).split

def lines_count(line_length, lines)
  count = []

  (0..line_length - 1).each do |i|
    zeros = 0
    ones  = 0

    lines.each do |line|
      case line[i].to_i
      when 0 then zeros += 1
      when 1 then ones += 1
      else break
      end
    end

    count << { ones: ones, zeros: zeros }
  end
  count
end

def power_consumption(lines)
  gamma = ''
  epsilon = ''

  lines_count(lines[0].length, lines).each do |line|
    if line[:zeros] > line[:ones]
      gamma += '0'
      epsilon += '1'
    elsif line[:ones] > line[:zeros]
      gamma += '1'
      epsilon += '0'
    end
  end
  gamma.to_i(2) * epsilon.to_i(2)
end

module RatingModes
  OXYGEN = 'oxygen'
  SCRUBBER = 'scrubber'
end

def rating(lines, rating_mode)
  filtered_lines = lines

  (0..lines[0].length - 1).each do |i|
    zeros = 0
    ones  = 0

    filtered_lines.each do |line|
      case line[i].to_i
      when 0 then zeros += 1
      when 1 then ones += 1
      else break
      end
    end

    filtered_lines = filtered_lines.select do |l|
      l[i].to_i == (
        case rating_mode
        when RatingModes::OXYGEN then zeros > ones ? 0 : 1
        when RatingModes::SCRUBBER then zeros > ones ? 1 : 0
        else break
        end
      )
    end

    return filtered_lines[0] if filtered_lines.length == 1
  end
end

def life_support_rating(lines)
  oxygen_generator_rating = rating(lines, RatingModes::OXYGEN)
  co2_scrubber_rating = rating(lines, RatingModes::SCRUBBER)
  oxygen_generator_rating.to_i(2) * co2_scrubber_rating.to_i(2)
end

power_consumption = power_consumption(lines)
life_support_rating = life_support_rating(lines)

puts "power_consumption = #{power_consumption}."
puts "life_support_rating = #{life_support_rating}."
