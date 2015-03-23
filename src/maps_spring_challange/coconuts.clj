;; Suppose three sailors are stranded on an island. They spend the first day
;; on the island collecting cocounts, agreeing to divide the coconuts the
;; day after. Afraid of missing out, one by one they wake up in the night
;; and take half of the remaining coconuts and give half a coconut to a
;; bystanding monkey. After the last sailor claimed his share, there was
;; only one coconut left, which was given to the monkey.
;;
;; How many coconuts were there in the original pile?
;;
;; This (easy) version of the coconut problem is from Martin Gardner, The
;; Colossal Book of Mathematics, W. W. Norton & Company 2001, pages 3-9.

(ns maps-spring-challenge.coconuts)

(-> 1 (+ 1/2) (* 2) (+ 1/2) (* 2) (+ 1/2) (* 2))

;; => 15N
