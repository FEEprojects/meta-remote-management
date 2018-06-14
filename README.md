This README file contains information on the contents of the
meta-remote-management layer.

Please see the corresponding sections below for details.


Overview
============

This is a rocko yocto layer that allows building cockpit for raspberry pi and integrating docker and ostree. Please see bellow the bblayers neccessary for building the remote-management-minimal image, that can be found in meta-remote-management/recipes-flybat/images/


Patches
=======

Maintainer: Mihaela Apetroaie-Cristea <apetroaiecristeamihaela@gmail.com>


I. Adding the remote-management layer to your build
=================================================


In order to use this layer, you need to make the build system aware of
it.

For building the full OS with docker, OSTree and Cockpit, you need the following layers:

    BBLAYERS ?= " \
    /path/to/yocto/meta \
    /path/to/yocto/meta-poky \
    /path/to/yocto/meta-remote-management \
    /path/to/yocto/meta-virtualization \
    /path/to/yocto/meta-openembedded/meta-oe \
    /path/to/yocto/meta-openembedded/meta-networking \
    /path/to/yocto/meta-openembedded/meta-systemd \
    /path/to/yocto/meta-openembedded/meta-python \
    /path/to/yocto/meta-openembedded/meta-filesystems \
    /path/to/yocto/meta-raspberrypi \
    /path/to/yocto//meta-rust \
    /path/to/yocto//oe-meta-go \
    /path/to/yocto/meta-updater \
    /path/to/yocto//meta-updater-raspberrypi \
    /path/to/yocto/meta-selinux \
    "

Adding new layers can be done by simply adding them and their dependecies (if any) to the list in /build/conf/bblayes.conf. For an example of how this file should look like, please see example-build-files in this directory.


II. Adding new packages
========================

Adding new packages can be done in build/local.conf by adding the following line

    IMAGE_INSTALL_append=" "

It can also be done in meta-remote-management/recipes-flybat/images/remote-management-minimal.bb, by adding them to:

    IMAGE_INSTALL_append=" openvpn autoconf cronie nano python-smbus python-pip i2c-tools vim gnupg docker cockpit-ws cockpit-dashboard cockpit-bridge cockpit-packages "

**The above line lists the packages installed with remote-management-minimal-image.**


III. Steps for building the OS distribution.
============================================

1. Download the poky repository, rocko version:

    git clone -b rocko git://git.yoctoproject.org/poky

2. Download and make sure you have all the rocko versions of the following layers in the poky repository: meta, meta-poky, meta-virtualization, meta-openembedded, meta-raspberrypi, meta-updater, meta-updater-raspberrypi. You will also need the master version of oe-meta-go (the rocko version was not available at the moment when this layer was developed)

* cd pocky

* git clone -b rocko git://git.yoctoproject.org/meta-virtualization

* git clone -b rocko git://git.openembedded.org/meta-openembedded

* git clone -b rocko git://git.yoctoproject.org/meta-raspberrypi

* git clone -b rocko https://github.com/advancedtelematic/meta-updater

* git clone -b rocko https://github.com/advancedtelematic/meta-updater-raspberrypi

* git clone -b rocko https://github.com/mem/oe-meta-go.git


3. In the poky directory, that contains all the neccessary layers listed above, you need to run the following command:

    source oe-init-build-env

This will create a build directory. This directory will contain a conf folder that will have 2 files: local.conf and bblayers.conf. The example-build-files directory in this repo has examples of files used to build images with this layer. The easiest way to build an OS distribution based on this layer is to replace the automatically generated files with the ones in this repo.

Make sure the distro in `local.conf` is set to

    DISTRO ?= "remote-management-sota"

4. To build the image run

    bitbake builder remote-management-minimal

5. Once the build process is done, you can find the image in `build/tmp/deploy/images/raspberrypi3/remote-management-minimal-raspberrypi3.wic`



## config.txt notes

u-boot and fitImages (the current setup) don't seem to use the dtparam= and dtoverlay= configs from config.txt.

The whole device tree is in the fitImage (linux kernel image, located on partition 2 of sd card at `/boot/ostree/poky-xxx/vmlinuz`).

SPI and I2C should be enabled by default, this is achieved with a kernel patch that changes the default values.

## Prelinking increases the size of downloads and causes build errors

    USER_CLASSES_remove = "image-prelink"
    VIRTUAL-RUNTIME_init_manager = "systemd"
    VIRTUAL-RUNTIME_initscripts = "systemd-compat-units"
