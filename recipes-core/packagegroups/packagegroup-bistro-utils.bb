#
#   Copyright (C) 2017 Pelagicore AB
#
DESCRIPTION = "Packagegroups that add useful development tools to meta-bistro."
LICENSE = "MIT"

PR = "r1"

inherit packagegroup

PROVIDES = "${PACKAGES}"
PACKAGES = "\
    packagegroup-bistro-utils \
    "

RDEPENDS_packagegroup-bistro-utils= "\
    alsa-utils-amixer \
    alsa-utils-alsamixer \
    connman-client \
    ethtool \
    fbset \
    gawk \
    gdbserver \
    ldd \
    lsof \
    ltrace \
    net-tools \
    perf \
    rsync \
    swaplogger \
    valgrind \
    vim \
    "
