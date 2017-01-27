#!/bin/bash
set -eux

docker run --rm -p 8081:8081 -p 3000:3000 netoht/uati:latest
