;; You have cracked your way into Sony (Again!) and found your friend's
;; account information. You would like to know his password, but you only
;; have the MD5 hash. Luckily, Sony has not discovered the importance of
;; using proper crypto algorithms or salts, and your friend has a lousy
;; password policy (for instance, his password contains only letters in
;; lower case).
;;
;;     The hash is 3a8703f560b3768e0277094c58c686e1.
;;
;; What is the password?

(ns maps-spring-challenge.md5-crack
  (:require [clojure.string :as str]
            [clojure.math.combinatorics :refer [cartesian-product]]
            [digest :as digest]))

(let [hash "3a8703f560b3768e0277094c58c686e1"
      alphabet (map #(char (+ 97 %)) (range 26))
      passwords ((fn ! [n] (lazy-cat (->> alphabet
                                          (repeat n)
                                          (apply cartesian-product)
                                          (map #(apply str %)))
                                     (! (inc n)))) 1)]
  (->> passwords
       (filter #(= hash (digest/md5 %)))
       (first)))

;; => "ghjkl"
;; "Elapsed time: 12863.479869 msecs"
