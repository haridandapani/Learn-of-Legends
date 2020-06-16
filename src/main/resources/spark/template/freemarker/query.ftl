<html>
<link rel="stylesheet" href="/css/style.css">
  <title>Learn of Legends</title>
  <div class = "column">
    <p> Enter the number of questions for your quiz</p>
    <form method="POST" action="/quiz">
      <input type = number id="numQuestions" name = "numQuestions" min = "1" max = "100" step = "1" required></input><br>
      <input type="checkbox" id="showAfterQuestion" name="showAfterQuestion" value="showAfterQuestion">
      <label for="showAfterQuestion"> Show answer after each question: </label><br>
      <input type="submit" value="Start Quiz">
    </form>
    <p>${message}</p>
    <br>

    <#if quiz??>
      <#list quiz as entry>
        <hr>
        <p class = "question"> ${entry.question}</p>
        <p>
        <#if entry.choices??>
          <#list 0..entry.choices?size-1 as i>
              <#if entry.choices[i].correct>
                  <span style="background-color:#90ee90">${entry.choices[i].text}</span>
              <#elseif i == entry.submitted && !entry.choices[i].correct>
                  <span style="background-color:#ffcccb">${entry.choices[i].text}</span>
              <#else>
                  <span>${entry.choices[i].text}</span>
              </#if>
              <br><br>
          </#list>
        </#if>
        <br>
      </#list>
    </#if>  
  </div>
</html>