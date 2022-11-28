#!/bin/bash

set -eu

if [ "$EUID" -eq 0 ]
   then printf "Please do not run as root"
   exit;
fi

function main(){
 read -p "SCWEB username:" USERNAME;
 ssh -L 3306:localhost:3306 $USERNAME@php.scweb.ca
}
main
