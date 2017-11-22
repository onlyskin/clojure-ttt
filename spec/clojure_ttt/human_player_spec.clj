(ns clojure-ttt.human-player-spec
  (:require [speclj.core :refer :all]
            [clojure-ttt.human-player :refer :all]))

(defn- vec-for-string [board-string]
  (clojure.string/split board-string #""))

(describe "get-human-move"
  (it "gets valid move from input"
    (should= 3 (with-in-str "20\nst\n3\n"
                 (get-human-move (vec-for-string "         "))))))

