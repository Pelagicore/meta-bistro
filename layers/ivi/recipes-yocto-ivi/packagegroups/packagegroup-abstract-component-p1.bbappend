#
#   Copyright (C) 2015 Pelagicore AB
#
python () {
    rdep = d.getVar('RDEPENDS_packagegroup-abstract-component-p1', d, 1)
    if rdep:
        rdep = rdep.replace("layer-management", "")
        d.setVar('RDEPENDS_packagegroup-abstract-component-p1', rdep)
}
