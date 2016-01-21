#
#   Copyright (C) 2015 Pelagicore AB
#   All rights reserved.
#
# Use local $PN directory
FILESEXTRAPATHS_prepend := "${THISDIR}/${PN}:"

# Add patch to avoid overwriting libc_cv_rootsbindir
SRC_URI += " \
    file://eglibc_2.19_add_af_bus_support.patch \
    "
