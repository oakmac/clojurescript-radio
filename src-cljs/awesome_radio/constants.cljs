(ns awesome-radio.constants
  "Just some constants.")

(def min-volume 0)
(def max-volume 10)

(def min-am-frequency 540)
(def max-am-frequency 1600)
(def am-frequency-interval 10)

(def min-fm-frequency 87.9)
(def max-fm-frequency 107.9)
(def fm-frequency-interval 0.2)

(def the-buzz
  {:band "FM"
   :frequency 94.5})

(def krbe
  {:band "FM"
   :frequency 104.1})

(def sports-radio-610
  {:band "AM"
   :frequency 610})

(def ktru
  {:band "FM"
   :frequency 96.1})

(def streams
  {"FM" 
      { 96.1 "http://128.42.128.29/live" }
   "AM"
      {}
  })
