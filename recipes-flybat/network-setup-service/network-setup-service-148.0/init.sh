#!/bin/sh

sleep 10

ifup eth0

sleep 10

openvpn --config /home/root/piec8b8d.ovpn

echo "Running network-setup-service package, network-vpn-setup.service init.sh at $(date)..." >> /home/root/trying_my_script.txt
