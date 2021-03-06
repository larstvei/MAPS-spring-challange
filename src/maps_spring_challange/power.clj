;; This file contains 1000 lines, each with two numbers a and b. Each line
;; represents a power, namely a^b.
;;
;; Now we want to know; which line (0-indexed) represents the largest
;; number?

(ns maps-spring-challenge.power
  (:require [clojure.string :as str]
            [clojure.java.io :as io]))

(let [logs (->> (io/file (io/resource "power.dat"))
                (slurp)
                (str/split-lines)
                (map #(map read-string (str/split % #" ")))
                (map #(* (second %) (Math/log (first %)))))]
  (.indexOf logs (reduce max logs)))

;; => 550

