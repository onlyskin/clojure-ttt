(ns clojure-ttt.ui-spec
  (:require [speclj.core :refer :all]
            [clojure-ttt.board :refer :all]
            [clojure-ttt.ui :refer :all]))

(describe "output-board"
  (it "prints board out"
    (should= "1|2|3\n-----\n4|5|6\n-----\n7|8|9\n"
      (with-out-str (output-board (make-board))))))
