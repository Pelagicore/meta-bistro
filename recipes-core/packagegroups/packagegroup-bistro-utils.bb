#
#   Copyright (C) 2017 Pelagicore AB
#
DESCRIPTION = "Packagegroups that add useful development tools to meta-bistro."
LICENSE = "MIT"

PR = "r1"

inherit packagegroup

PROVIDES = "${PACKAGES}"
PACKAGES = "packagegroup-bistro-utils"

RDEPENDS_packagegroup-bistro-utils = " \
    alsa-utils-amixer                  \
    alsa-utils-alsamixer               \
    connman                            \
    connman-client                     \
    ethtool                            \
    fbset                              \
    gawk                               \
    gdbserver                          \
    git                                \
    ldd                                \
    lsof                               \
    ltrace                             \
    net-tools                          \
    openssh-sftp-server                \
    perf                               \
    rsync                              \
    swaplogger                         \
    systemd-additional-units           \
    systemd-analyze                    \
    valgrind                           \
    vim                                \
"
