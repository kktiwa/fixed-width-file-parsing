# Problem Statement

To support different analytics and reporting functions, Data engineering teams often work with data coming from different systems. All of the systems don't support the same file formats. Consider one system can only send data in fixed-width file format, and to be able to load this data into a database you have to convert it into a CSV file format.

- Generate a fixed-width file using the provided spec `spec.json` (offset provided in the spec file represent the length of each field)
- Implement a parser that can parse the fixed width file and generate a delimited CSV file
- DO NOT use python libraries like pandas for parsing. You can use the standard library to write out a CSV file

## Solution Approach
A decoupled approach to parse the `spec.json` file, generate fixed size width file
and finally use the parsed specification config to generate the CSV file
- Fixed size width is created by parsing the `spec.json` file
- Csv parser uses the fixed size width file to generate an output CSV

### Assumptions
- Smaller rows of data will be passed to the parser logic to handle scale either via batching or streaming

### Building and Running

To compile the source and tests run:
```sbt
sbt test:compile
```

Run the following command to execute tests:
```sbt
sbt test
```

Run the following command to execute the `Main` application:
The following files would be created under `/data` folder:
- `input.txt` is the generated fixed width file
- `output.csv` is the parsed csv file

### Code Coverage

Run the following command to instrument code and tests:
```sbt
sbt clean coverage test
```

Run the following command to generate a coverage report:
```sbt
sbt coverageReport
```

### Improvements
- Error handling
- Logging
- Dockerizing the application correctly