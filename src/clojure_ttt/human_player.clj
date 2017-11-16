(ns clojure-ttt.human-player
  (:require [clojure-ttt.ui :refer :all]))

(defn get-human-move [board]
  (input-move board))
