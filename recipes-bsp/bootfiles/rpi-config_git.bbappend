

do_deploy_append() {
  bbplain yo this append is running 342
  echo "# add spi-gpio-cs overlay" >> ${DEPLOYDIR}/bcm2835-bootfiles/config.txt
  echo "dtoverlay=spi-gpio-cs" >> ${DEPLOYDIR}/bcm2835-bootfiles/config.txt
  echo "dtoverlay=pi3-disable-bt" >> ${DEPLOYDIR}/bcm2835-bootfiles/config.txt
  echo "dtoverlay=pps-gpio,gpiopin=6" >> ${DEPLOYDIR}/bcm2835-bootfiles/config.txt
  echo "dtoverlay=pi3-miniuart-bt" >> ${DEPLOYDIR}/bcm2835-bootfiles/config.txt
  echo "dtoverlay=i2c-rtc,ds1307" >> ${DEPLOYDIR}/bcm2835-bootfiles/config.txt
#
#   echo "dtoverlay=pi3-disable-bt" >> ${DEPLOYDIR}/bcm2835-bootfiles/config.txt
#   echo "dtoverlay=pps-gpio,gpiopin=18" >> ${DEPLOYDIR}/bcm2835-bootfiles/config.txt
#
}
