# Problem Statement

To support different analytics and reporting functions, Data engineering teams often work with data coming from different systems. All of the systems don't support the same file formats. Consider one system can only send data in fixed-width file format, and to be able to load this data into a database you have to convert it into a CSV file format.

- Generate a fixed-width file using the provided spec `spec.json` (offset provided in the spec file represent the length of each field)
- Implement a parser that can parse the fixed width file and generate a delimited CSV file
- DO NOT use python libraries like pandas for parsing. You can use the standard library to write out a CSV file