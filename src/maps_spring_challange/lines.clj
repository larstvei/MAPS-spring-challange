;; This file represents 1000 points in the plane. Each point is described on
;; a single line by its x-coordinate and y-coordinate (see the file).
;;
;; The line that runs through the points (0, 0) and (1000, 1000) splits this
;; set of points in two.
;;
;; How many points are there on the side of the line which has the most
;; points? (you may assume that no points lie exactly on the line)

(ns maps-spring-challenge.lines
  (:require [clojure.string :as str]
            [clojure.java.io :as io]))

(defn determine-side [sides [x y]]
  (update-in sides [(if (> x y) :right :left)] conj [x y]))

(let [points (->> (io/file (io/resource "lines.dat"))
                  (slurp)
                  (str/split-lines)
                  (map #(vec (map read-string (str/split % #" ")))))
      sides (reduce determine-side {:right [] :left []} points)]
  (reduce max (map count (vals sides))))

;; => 506
