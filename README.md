[![License](http://img.shields.io/:license-mit-blue.svg?style=flat-square)](http://badges.mit-license.org)

# meta-bistro

A layer to provide IVI related functionality in an OpenEmbedded Linux system.

## Dependencies

This layer currently depends on:

* poky (http://git.yoctoproject.org/cgit/cgit.cgi/poky)
* meta-openembedded (http://cgit.openembedded.org/meta-openembedded/)

### Optional dependencies

These are optional dependencies which we have additions for through the layers
functions described below:

* meta-qt5 (https://github.com/meta-qt5/meta-qt5)
* meta-ivi (https://github.com/GENIVI/meta-ivi)
* meta-openembedded/meta-multimedia
* meta-openembedded/meta-networking

## Layers

Depending on included layers used together with meta-bistro additional
functionality is added from the `layers/*/` directories. This is done by adding
the `layers/*` directory if the name is in the `BBFILE_COLLECTIONS` variable,
as defined in the `conf/layer.conf` file.

The `BBFILE_COLLECTIONS` contains the names as given by each respective meta
layer, and hence the names of the directories can not be changed by us directly.

The general idea is that These layer additions should only be added to
meta-bistro if those other layers are available.

## Governance

This repository is maintained and governed by Pelagicore AB, we will consider
all contributions and anyone is free to fork this work.

Maintainers:

 * Gordan Markus <gordan.markus@pelagicore.com>
 * Oscar Andreasson <oscar.andreasson@pelagicore.com>

## Branching

This repository will follow the yocto release system. Whenever a new yocto
release has been released, a new branch with the same name will be created
from the master branch.
All feature growth should happen first on the master branch, but will also be
cherry picked back to the latest yocto release branch. Security and bug fixes
will be evaluated case by case and backported as necessary. The ambition is to
actively maintain the four latest releases and/or two year old releases in
this fashion.

## License and Copyright

Copyright (C) 2015-2017 Pelagicore AB

All metadata is MIT licensed unless otherwise stated. Source code included
in tree for individual recipes is under the LICENSE stated in the associated
recipe (.bb file) unless otherwise stated.

License information for any other files is either explicitly stated
or defaults to MIT license.
