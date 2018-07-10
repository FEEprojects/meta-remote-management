#!/bin/bash

# this script assumes this is being tracked in a git repo and you want to add
# all dependecy layers as git submodules

# oe-meta-go
git submodule add https://github.com/mem/oe-meta-go.git oe-meta-go

# Meta-virtualisation
git submodule add -f -b morty git://git.yoctoproject.org/meta-virtualization meta-virtualization

# meta-raspberrypi
git submodule add -b morty git://git.yoctoproject.org/meta-raspberrypi meta-raspberrypi

# meta-rust
git submodule add -b morty https://github.com/meta-rust/meta-rust.git meta-rust

# meta-updater
git submodule add -b morty https://github.com/advancedtelematic/meta-updater.git meta-updater

# meta-updater-raspberrypi
git submodule add -b morty https://github.com/advancedtelematic/meta-updater-raspberrypi.git meta-updater-raspberrypi

# meta-selinux
git submodule add -b morty git://git.yoctoproject.org/meta-selinux meta-selinux

# meta-openembedded
git submodule add -b morty https://github.com/openembedded/meta-openembedded.git meta-openembedded
