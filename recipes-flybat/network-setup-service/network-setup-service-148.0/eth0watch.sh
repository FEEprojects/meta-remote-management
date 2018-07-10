#!/bin/sh

echo "$(date): Starting eth0 watcher... modprobe i2c-dev" >> /var/log/eth0watch.log

ifup eth0

sleep 10

timeout=5           # delay between checks
pingip="8.8.8.8"    # what to ping
iface="eth0"
LOG_FILE="/var/log/eth0log"
isdown=0            # indicate whether the interface is up or down
                    # start assuming interface is up

echo "$(date): Starting eth0 main loop... modprobe i2c-dev" >> /var/log/eth0watch.log

while true; do
    LOG_TIME=`date +%b' '%d' '%T`
    if ping -q -c 2 "$pingip" >> /dev/null ; then      # ping is good - bring iface up
        if [ "$isdown" -ne 0 ] ; then
            ifup $iface && isdown=0
            printf "$LOG_TIME $0: Interface brought up: %s\n" "$iface" | tee -a $LOG_FILE
        fi
    else                                 # ping is bad - bring iface down
        beep -f 4000
        if [ "$isdown" -ne 1 ] ;  then
            ifdown $iface && isdown=1
            printf "$LOG_TIME $0: Interface brought down: %s\n" "$iface" | tee -a $LOG_FILE
        fi
    fi

    sleep "$timeout"

done
