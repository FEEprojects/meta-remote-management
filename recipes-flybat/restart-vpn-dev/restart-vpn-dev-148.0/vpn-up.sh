#!/bin/bash
if [ -n "$1" ]; then
       echo "Sleeping for $1 seconds before checking whether to (re)start IPv6 tunnel."
       sleep $1
fi
if [ `ping6 -c 1 2001:630:d0:f30c::2:1 2>&1 | grep "connect: Network is unreachable" | wc -l` -eq 1  ]; then
       echo -e "[`date +%H:%M:%S`] Restarting IPv6 Tunnelbroker (Network is unreachable)\n"
       killall -9 openvpn 2>/dev/null
       sleep 3
       grep "bla" >/home/root/is_tun_on.txt
       /home/root/connect.sh
elif [ `ping6 -c 10 2001:630:d0:f30c::2:1 | grep "100% packet loss" | wc -l` -eq 1 ]; then
       echo -e "[`date +%H:%M:%S`] Restarting IPv6 Tunnelbroker (100% Packet loss)\n"
       killall -9 openvpn 2>/dev/null
       sleep 3
       /home/root/connect.sh
elif [ `/sbin/ifconfig tun0  2>/dev/null | wc -l` -lt 1 ]; then
       echo -e "[`date +%H:%M:%S`] Starting IPv6 Tunnelbroker after boot\n"
       killall -9 openvpn 2>/dev/null
       sleep 3
       /home/root/connect.sh
else
       echo -e "[`date +%H:%M:%S`] Not Restarting IPv6 Tunnelbroker\n"
fi

