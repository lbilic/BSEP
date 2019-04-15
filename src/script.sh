#!/bin/bash

echo "Which user is the script for(type pki, siem or user): "

read response
path=$( cd "$(dirname "${BASH_SOURCE[0]}")" ; pwd -P )"/main/resources/jks"

if [ $response  = "pki" ]; then
    find $path -type f -iname "*.jks" -exec chmod 700 {} \;
elif [ $response = "siem" ]; then
    find $path -type f -iname "*.jks" -exec chmod 400 {} \;
elif [ $response = "user" ]; then
    find $path -type f -iname "*.jks" -exec chmod 000 {} \;
else
    echo "Unknown user" $response
    exit 1
fi
