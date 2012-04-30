#!/bin/sh
mysql --user=$1 --password=$2 < sql/initBd.sql
exit 0
