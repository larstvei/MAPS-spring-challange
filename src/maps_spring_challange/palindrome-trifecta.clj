;; A palindrome is a number that is the same "forwards and backwards". For
;; instance, 12321 and 1331 are palindromes. More precisely, a number is
;; palindromic if the i-th first digit is the same as the i-th last digit.
;;
;; We are interested in the smallest three-digit palindrome that can be
;; written as the sum of three unique three-digit palindromes.

(ns maps-spring-challenge.palindrome-trifecta)
;; Turns out the three smallest three-digit palindromes produce a
;; three-digit palindrome:

(+ 101 111 121)

;; => 333
