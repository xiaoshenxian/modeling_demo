# Modeling demo

This is a demo of using the ranking and recommendation framework in javaxsx.

## The framework

Do you want to write a recommendation system in only 8 lines? Try javaxsx here:

[Nexus Repository Manager](https://oss.sonatype.org/#nexus-search;gav~com.eroelf~javaxsx~~~)

[Maven Central Repository](http://search.maven.org/#search%7Cga%7C1%7Cjavaxsx)

```
<dependency>
    <groupId>com.eroelf</groupId>
    <artifactId>javaxsx</artifactId>
    <version>${version}</version>
</dependency>
```

[Github](https://github.com/xiaoshenxian/javaxsx)

**The working flow is defined as the following 8 steps:**

1. Parse request parameters.

2. Create a *Converter* instance for converting back-end data to the front-end data the requester need.

3. Create a *FlowHandler* instance defining the entire working flow behaviors, including the requester statistics computation, candidates generator, recall *Strategies*, *Modelers* for calculating feature, and *Scorers* for running models to give out the final score.

4. Create a *ItemFilterHandler* to deal with *Item* filters.

5. Create a *ItemGenerator* assembling all the requesting information, the *FlowHandler*, and the *ItemFilterHandler*.

6. Invoke the *ItemGenerator::generate* method to get the featured and scored *Item* list.

7. Sort the *Item* list.

8. Use the *Converter* instance created in step 2 to convert the back-end product (*Item*) list to a front-end product (*Info*) list, as well as logging necessary information for each *Info* object to a logger data structure (*InfoLog*).

## Enumerable working flow and Innumerable working flow

An **Enumerable** working flow means that the *Item* candidates can be recalled by one or more simple enumerating progresses.

An **Innumerable** working flow means that the *Item* candidates cannot be recalled by enumerating, but only by *Strategies*, meaning that one cannot obtain all candidates until all those *Strategies* are executed.

Candidates given by a *Strategy* can be either enumerable or innumerable. This demo shows both cases.

Please note that the innumerable working flow can also deal with the enumerable working flow, choices would be made due to personal preferences.

## Demo entrances

* *Enumerable*: com.eroelf.demo.modeling.flow.enumerable.Main

* *Innumerable*: com.eroelf.demo.modeling.flow.innumerable.Main

One invoking of the main function simulates one request. The demo shows what roles those components play in the whole progress, as well as how the working flow assembles them and works with the data flow.

Please note that since there is no real data, the demo would not give out correct result and even would throw exceptions.

Please refer to the javaxsx Javadoc for further information.
