---
{
    "title": "array_pushback",
    "language": "en"
}
---

<!-- 
Licensed to the Apache Software Foundation (ASF) under one
or more contributor license agreements.  See the NOTICE file
distributed with this work for additional information
regarding copyright ownership.  The ASF licenses this file
to you under the Apache License, Version 2.0 (the
"License"); you may not use this file except in compliance
with the License.  You may obtain a copy of the License at
  http://www.apache.org/licenses/LICENSE-2.0
Unless required by applicable law or agreed to in writing,
software distributed under the License is distributed on an
"AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY
KIND, either express or implied.  See the License for the
specific language governing permissions and limitations
under the License.
-->

## array_pushback

<version since="2.0">

array_pushback

</version>

### description

#### Syntax

`Array<T> array_pushback(Array<T> arr, T value)`

Add the value to the end of the array.

#### Returned value

The array after adding the value.

Type: Array.

### notice

`Only supported in vectorized engine`

### example

```
mysql> select array_pushback([1, 2], 3);
+---------------------------------+
| array_pushback(ARRAY(1, 2), 3)  |
+---------------------------------+
| [1, 2, 3]                       |
+---------------------------------+

mysql> select col3, array_pushback(col3, 6) from array_test;
+-----------+----------------------------+
| col3      | array_pushback(`col3`, 6)  |
+-----------+----------------------------+
| [3, 4, 5] | [3, 4, 5, 6]               |
| [NULL]    | [NULL, 6]                  |
| NULL      | NULL                       |
| []        | [6]                        |
+-----------+----------------------------+

mysql> select col1, col3, array_pushback(col3, col1) from array_test;
+------+-----------+---------------------------------+
| col1 | col3      | array_pushback(`col3`, `col1`)  |
+------+-----------+---------------------------------+
|    0 | [3, 4, 5] | [3, 4, 5, 0]                    |
|    1 | [NULL]    | [NULL, 1]                       |
|    2 | NULL      | NULL                            |
|    3 | []        | [3]                             |
+------+-----------+---------------------------------+
```

### keywords

ARRAY,PUSHBACK,ARRAY_PUSHBACK