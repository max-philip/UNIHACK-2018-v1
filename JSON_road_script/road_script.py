import json
from collections import defaultdict as dd

# Make it work for Python 2+3 and with Unicode
import io
try:
    to_unicode = unicode
except NameError:
    to_unicode = str

# Always concatenates the point keys with the key that is
# evaluated lower FIRST = ensures all unique keys

def gen_road_key(pos1, pos2):
    key = ""
    if (pos1 < pos2):
        key = pos1 + pos2
    else:
        key = pos2 + pos1

    return key


def set_value(pos1, pos2, roads_dict, field, value):
    key = gen_road_key(pos1, pos2)
    roads_dict[key][field] = value


with open('data.json', 'r') as f:
    array = json.load(f)

print(array)

points = array["map"]["points"]

roads = dd()

for loc in points.keys():
    print(loc)
    for neb in points[loc]["neighbours"]:
        key = gen_road_key(loc, neb)
        roads[key] = {"crime": "0", "elevation": "0", "lights": "0", \
        "parks": "0", "usr-NICE": "0", "usr-SAFE": "0"}

print(len(roads.keys()))
print(roads)

with io.open('roads.json', 'w', encoding='utf8') as outfile:
    str_ = json.dumps(roads,
                      indent=4, sort_keys=True,
                      separators=(',', ': '), ensure_ascii=False)
    outfile.write(to_unicode(str_))
