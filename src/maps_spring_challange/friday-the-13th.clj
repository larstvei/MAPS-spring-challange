;; We are now interested in friday the 13th, especially consecutive months
;; with friday the 13th.
;;
;; For instance this year, we had friday the 13th both in Febuary and March,
;; which are consecutive months. When is the next time (from today) Friday
;; the 13th will occur in two consecutive months?
;;
;; The answer should specify both of the consecutive months by year and
;; month number. For instance, for Feb and March 2015; 2015-02 2015-03

(ns maps-spring-challange.friday-the-13th
  (:require [clojure.string :as str]
            [clj-time.core :as t]
            [clj-time.predicates :as t?]
            [clj-time.periodic :as tp]
            [clj-time.format :as tf]))

(let [format (tf/formatters :year-month)
      days (-> (t/now) (t/first-day-of-the-month) (t/plus (t/days 12))
               (tp/periodic-seq (t/months 1)))]
  (loop [[t1 t2 & days] days]
    (if (every? t?/friday? [t1 t2])
      (->> [t1 t2] (map #(tf/unparse format %)) (str/join " " ))
      (recur (cons t2 days)))))

;; => "2026-02 2026-03"
