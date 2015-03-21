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

(ns maps-spring-challenge.md5-crack)
(require '[clojure.string :as str])
(import 'java.security.MessageDigest)
(import 'java.security.DigestInputStream)

(defn md5 [s]
  (->> (.digest (doto (java.security.MessageDigest/getInstance "MD5")
                  .reset (.update (.getBytes s))))
       (map (partial format "%02x"))
       (apply str)))

(defn mod-list [num m]
  (if (< num m)
    (conj [] num)
    (let [d (Math/pow m (int (/ (Math/log num) (Math/log m))))]
      (conj (mod-list (int (mod num d)) m) (dec (int (/ num d)))))))

(def mod-list (memoize mod-list))

(defn num->pass [num]
  (apply str (map #(char (+ 97 %)) (mod-list num 26))))

(time
 (loop [strs (map num->pass (iterate inc 0))]
   (if (= "3a8703f560b3768e0277094c58c686e1" (md5 (first strs)))
     (first strs)
     (recur (rest strs)))))

;;    "Elapsed time: 170847.74092 msecs"
;; => "ghjkl"
