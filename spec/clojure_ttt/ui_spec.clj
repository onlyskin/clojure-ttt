(ns clojure-ttt.ui-spec
  (:require [speclj.core :refer :all]
            [clojure-ttt.board :refer :all]
            [clojure-ttt.ui :refer :all]))

(describe "output-board"
  (it "prints board out"
    (should= "1 2 3\n4 5 6\n7 8 9"
      (with-out-str (output-board (make-board))))))
