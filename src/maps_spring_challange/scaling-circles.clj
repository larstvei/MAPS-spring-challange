;; A circle A has an area of 52 square cm.
;;
;; Suppose you wanted to draw another circle B with an area equal to 37% of
;; A. What is the radius of B?
;;
;; Write your answer in decimal notation with one digit after the period,
;; like "6.3".

(ns maps-spring-challenge.scaling-circles)

(format "%.1f" (-> 52 (* 0.37) (/ Math/PI) (Math/sqrt)))

;; => "2.5"
