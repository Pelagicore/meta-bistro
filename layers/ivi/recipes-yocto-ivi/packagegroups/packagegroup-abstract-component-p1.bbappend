#
#   Copyright (C) 2015 Pelagicore AB
#   All rights reserved.
#
python () {
    rdep = bb.data.getVar('RDEPENDS_packagegroup-abstract-component-p1', d, 1)
    if rdep:
        rdep = rdep.replace("layer-management", "")
        bb.data.setVar('RDEPENDS_packagegroup-abstract-component-p1', rdep, d)
}
