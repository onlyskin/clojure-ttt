(ns clojure-ttt.ui
  (:require [clojure-ttt.board :refer :all]))

(declare board-str)

(defn output [message] (println message))

(defn output-board [board]
  (output (board-str board)))

(defn output-game-result [board]
  (cond
    (winner? board "X") (output "X won.")
    (winner? board "O") (output "O won.")
    (tie? board) (output "The game was a tie.")))

(defn input [prompt]
  (println prompt)
  (read-line))

(defn input-integer []
  (try
    (Integer/parseInt (str (read-line)))
    (catch Exception e (input-integer))))

(defn- inner-string [index, value]
  (cond
    (not= " " value) value
    :else (str (+ 1 index))))

(defn- cell-string [index, value] 
  (cond
    (= index 8) (inner-string index value)
    (= 2 (mod index 3)) (str (inner-string index value) "\n-----\n")
    :else (str (inner-string index value) "|")))

(defn- cell-strings [board]
  (map-indexed #(cell-string %1, %2) board))

(defn- board-str [board]
  (clojure.string/join
    (cell-strings board)))
