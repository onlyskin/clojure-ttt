(ns clojure-ttt.ui
  (:require [clojure-ttt.board :refer :all]))

(defn- cell-string [index, cell] 
  (cond
    (= index 8) (str (+ 1 index))
    (= 2 (mod index 3)) (str (+ 1 index) "\n-----\n")
    :else (str (+ 1 index) "|")))

(defn- cell-strings [board]
  (map-indexed #(cell-string %1, %2) board))

(defn- board-str [board]
  (clojure.string/join
    (cell-strings board)))

(defn output-board [board]
  (println
    (board-str board)))
