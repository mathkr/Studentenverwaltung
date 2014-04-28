import Data.Numbers.Primes

main :: IO()
main = putStr . unlines . map show . getPrimes $ 80

getPrimes :: (Integral a) => a -> [a]
getPrimes max = [smallerPrime x | x <- powersOfTwo max]
	where
	 powersOfTwo max = [2 ^ x | x <- [0..max]]

smallerPrime :: (Integral a) => a -> a
smallerPrime 1 = 1
smallerPrime n = if isPrime n then n
			      else smallerPrime $ n - 1
