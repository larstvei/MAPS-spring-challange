;;  This file encodes a directed graph with 25 nodes, numbered from 0
;;  through 24. The file consists of 25 rows, each row having 25
;;  columns. For a given row i, the value in column j represents the cost of
;;  going from node i to node j. If the cost is -1, there is no edge from
;;  node i to node j. Unless the cost is -1, it will always be positive.
;;
;; Find the shortest path from node 0 to node 24. For each edge on the path
;; from node 0 to node 24, print the ascii symbol encoded by cost of the
;; edge % 128.
;;
;; For example, if the path consists of three edges with costs 59, 833 and
;; 1595, the answer is YAY.
;;
;; The edges must be printed in the order they are visited when starting in
;; node 0 and ending in node 24.

(ns maps-spring-challenge.keyword
  (:require [clojure.string :as str]
            [clojure.java.io :as io]
            [clojure.data.priority-map :refer :all]))

(defn node [i table]
  [i (->> table
          (map-indexed #(vector %1 %2))
          (filter (fn [[i e]] (pos? e))))])

(let [target 24
      graph (->> (io/file (io/resource "graph.tsv"))
                 (slurp)
                 (str/split-lines)
                 (map #(vec (map read-string (str/split % #"\t"))))
                 (map-indexed node)
                 (vec))]
  (loop [queue (priority-map (conj (graph 0) []) 0)]
    (let [[[i edges path] dist] (peek queue)
          q (reduce (fn [r [j d]]
                      (let [node (conj (graph j) (conj path d))]
                        (conj r [node (+ d dist)])))
                    (pop queue) edges)]
      (if (= target i)
        (apply str (map #(char (mod % 128)) path))
        (recur q)))))
