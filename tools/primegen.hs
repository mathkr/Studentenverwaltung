import Data.Numbers.Primes

main :: IO()
main = mapM_ print . getPrimes $ 31

getPrimes :: (Integral a) => a -> [a]
getPrimes max = [smallerPrime x | x <- powersOfTwo max]
        where powersOfTwo max = [2 ^ x | x <- [0..max]]

smallerPrime :: (Integral a) => a -> a
smallerPrime 1 = 1
smallerPrime n
        | isPrime n = n
        | otherwise = smallerPrime $ n - 1
