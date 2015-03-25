;; This file represents 1000 points in the plane. Each point is described on
;; a single line by its x-coordinate and y-coordinate (see the file).
;;
;; What two points lie the furthest apart (in euclidean distance)?
;;
;; The answer should be formatted as a b c d, where (a, b) comes before (c,
;; d) in the file.
;;
;; Example: If the points (124, 533) and (925, 436) happened to be the two
;; points that are the furthest away from each other, the answer would be
;; 124 533 925 436, since (124, 533) comes before (925, 436) in the list.

(ns maps-spring-challenge.points-on-a-plane
  (:require [clojure.string :as str]
            [clojure.java.io :as io]))

(defn euclidean [[x1 y1] [x2 y2]]
  (Math/sqrt (+ (#(* % %) (- x1 x2))
                (#(* % %) (- y1 y2)))))

(let [points (->> (io/file (io/resource "lines.dat"))
                  (slurp)
                  (str/split-lines)
                  (map #(vec (map read-string (str/split % #" ")))))]
  (apply format "%d %d %d %d"
         (->> (for [p1 points p2 points] [(euclidean p1 p2) p1 p2])
              (apply max-key first)
              (rest)
              (sort #(< (.indexOf points %1) (.indexOf points %2)))
              (flatten))))

;; => "997 19 14 994"

