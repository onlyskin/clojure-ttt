(ns clojure-ttt.human-player
  (:require [clojure-ttt.ui :refer :all]))

(defn get-human-move [board]
  (get-move board))
