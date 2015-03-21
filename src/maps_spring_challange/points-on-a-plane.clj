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

(require '[clojure.string :as str])

(defn euclidean [[x1 y1] [x2 y2]]
  (reduce #(+ %1 (* %2 %2)) [(- x1 x2) (- y1 y2)]))

(let [points (->> (slurp "lines.dat")
                  (str/split-lines)
                  (map #(vec (map read-string (str/split % #" ")))))
      distances (map (fn [p] (map #(euclidean p %) points)) points)]
  (reduce max (map #(reduce max %) distances)))

;; now how do I retrieve the points...
