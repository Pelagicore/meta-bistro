#
#   Copyright (C) 2017 Pelagicore AB
#   Copyright (C) 2019 Luxoft Sweden AB
#
#   SPDX-License-Identifier: MIT
#
DESCRIPTION = "Packagegroups that add useful development tools to meta-bistro."
LICENSE = "MIT"

PR = "r1"

inherit packagegroup

PROVIDES = "${PACKAGES}"
PACKAGES = "packagegroup-bistro-utils \
            packagegroup-bistro-debug-utils"

RDEPENDS_packagegroup-bistro-utils = " \
    alsa-utils-amixer                  \
    alsa-utils-alsamixer               \
    ethtool                            \
    fbset                              \
    gawk                               \
    git                                \
    lsof                               \
    net-tools                          \
    openssh-sftp-server                \
    perf                               \
    rsync                              \
    vim                                \
"

RDEPENDS_packagegroup-bistro-debug-utils = " \
    gdbserver                                \
    ldd                                      \
    ltrace                                   \
    swaplogger                               \
    systemd-analyze                          \
    strace                                   \
    valgrind                                 \
"
