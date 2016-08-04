#
#   Copyright (C) 2015 Pelagicore AB
#
# Use bluez5 instead of 4
RDEPENDS_packagegroup-base-bluetooth = "\
    bluez5 \
    ${@base_contains('COMBINED_FEATURES', 'alsa', 'libasound-module-bluez', '',d)} \
    "
