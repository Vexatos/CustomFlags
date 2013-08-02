#!/bin/bash
NOW=$(date +"%m-%d-%Y %r")

cd ~/Documents/Mods/ModJam.git
git add .
git commit -m "Custom Flags Auto Commit $NOW"
git push

notify-send "Custom Flags Auto Commit $NOW"


