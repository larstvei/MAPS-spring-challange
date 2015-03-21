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
            [clojure.math.combinatorics :refer [cartesian-product]])
  (:import  (java.security.MessageDigest)
            (java.security.DigestInputStream)))

(defn md5 [s]
  (->> (.digest (doto (java.security.MessageDigest/getInstance "MD5")
                  .reset (.update (.getBytes s))))
       (map (partial format "%02x"))
       (apply str)))

(time
 (let [alphabet (map #(char (+ 97 %)) (range 26))
       passwords ((fn ! [n] (lazy-cat(->> alphabet
                                          (repeat n)
                                          (apply cartesian-product)
                                          (map #(apply str %)))
                                     (! (inc n)))) 1)]
   (->> passwords
        (drop-while #(not= "3a8703f560b3768e0277094c58c686e1" (md5 %)))
        (first))))

;;    "Elapsed time: 107830.707635 msecs"
;; => "ghjkl"
