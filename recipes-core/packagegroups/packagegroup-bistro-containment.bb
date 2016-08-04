#
#   Copyright (C) 2015 Pelagicore AB
#
DESCRIPTION = "Packagegroups which adds some tools to Bistro for application containment."
LICENSE = "MIT"

PR = "r1"

inherit packagegroup

PROVIDES = "${PACKAGES}"
PACKAGES = "\
    packagegroup-bistro-containment \
    "

RDEPENDS_packagegroup-bistro-containment= "\
    lxc \
    "

