;; Consider a classroom full of students.
;;
;; Assume that:
;;
;;  * The students are born within the same year a year consists of 365 days
;;
;;  * the probability of being born on a given day is uniformly distributed
;;    across the year and
;;
;;  * the probability of being born on a given minute of a day is uniformly
;;    distributed across the day
;;
;; How many students must be in the classroom for there to be at least a 50%
;; probability that two of the students are born within the same minute? By
;; within the same minute, we mean the same clock-minute, ie. a minute
;; starting at YYYY.MM.DD hh:mm:00.

(ns maps-spring-challenge.birthday-minute)

(let [minutes (float (* 365 24 60))
      probs (map #(vector % (/ (- minutes %) minutes)) (iterate inc 1))]
  (loop [[[i p] & ps] probs acc 1]
    (if (< 1/2 (- 1 acc))
      i
      (recur ps (* p acc)))))

;; => 854
