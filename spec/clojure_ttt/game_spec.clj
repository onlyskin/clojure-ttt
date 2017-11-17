(ns clojure-ttt.game-spec
  (:require [speclj.core :refer :all]
            [clojure-ttt.game :refer :all]
            [clojure-ttt.human-player :refer :all])) 

(defn out-str-from-run [moves]
  (with-out-str
    (with-in-str (clojure.string/join "\n" moves) (run-game [get-human-move get-human-move]))))

(describe "run game"
          (it "output prints empty board"
              (should-contain
                #"(?s)1.*2.*3.*4.*5.*6.*7.*8.*9"
                (out-str-from-run ["1" "4" "2" "5" "3"])))

          (it "output prints X"
              (should-contain
                "X"
                (out-str-from-run ["1" "4" "2" "5" "3"])))

          (it "output prints O"
              (should-contain
                "O"
                (out-str-from-run ["1" "4" "2" "5" "3"])))

          (it "ends" (should true))

          (it "output prints X won"
              (should-contain
                "X won"
                (out-str-from-run ["1" "4" "2" "5" "3"])))

          (it "rejects bad moves"
              (should-contain
                "X won"
                (out-str-from-run ["1" "4" "st" "2" "5" "20" "3"]))))
