#
#   Copyright (C) 2015 Pelagicore AB
#
DESCRIPTION = "Image for creating a small bootable Bistro-based image with meta-ivi (p1 and p2)"

require core-image-bistro.bb

# meta-ivi
IMAGE_INSTALL += "packagegroup-specific-component-p1 packagegroup-specific-component-p2"
