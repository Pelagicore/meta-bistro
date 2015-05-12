#
#   Copyright (C) 2015 Pelagicore AB
#   All rights reserved.
#
DESCRIPTION = "Packagegroups which adds some tools to Bistro that is potentially useful during development."
LICENSE = "MIT"

PR = "r1"

inherit packagegroup

PROVIDES = "${PACKAGES}"
PACKAGES = "\
    packagegroup-bistro-utils \
    "

RDEPENDS_packagegroup-bistro-utils= "\
    fbset \
    ldd \
    vim \
    gawk \
    ethtool \
    connman-client \
    alsa-utils-amixer \
    alsa-utils-alsamixer \
    gdbserver \
    rsync \
    valgrind \
    swaplogger \
    "

