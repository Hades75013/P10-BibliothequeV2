package com.ocr.library.batch;

import com.ocr.library.batch.step.PretFilterProcessorMail;
import com.ocr.library.batch.step.PretItemWriterMail;
import com.ocr.library.dao.PretDao;
import com.ocr.library.model.Pret;
import org.springframework.batch.core.*;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.launch.support.RunIdIncrementer;
import org.springframework.batch.core.launch.support.SimpleJobLauncher;
import org.springframework.batch.core.repository.JobRepository;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.batch.item.ItemWriter;
import org.springframework.batch.item.data.RepositoryItemReader;
import org.springframework.batch.item.data.builder.RepositoryItemReaderBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.scheduling.annotation.Scheduled;

import javax.mail.internet.MimeMessage;
import javax.sql.DataSource;
import java.util.*;

@Configuration
@EnableBatchProcessing
public class SpringBatch {

    @Autowired
    public JobBuilderFactory jobBuilderFactory;

    @Autowired
    public StepBuilderFactory stepBuilderFactory;

    @Autowired
    private SimpleJobLauncher jobLauncher;

    @Autowired
    PretDao pretDao;


    @Scheduled(cron = "0 10 11 * * *")
    public void relance() throws Exception {

        System.out.println("BatchJob Started at :" + new Date());

        JobParameters param = new JobParametersBuilder().addString("JobID", String.valueOf(System.currentTimeMillis()))
                .toJobParameters();

        JobExecution execution = jobLauncher.run(exportUserJob(myStep(myReader(pretDao),myProcessor(),myWriter())), param);

        System.out.println("BatchJob finished with status :" + execution.getStatus());
    }


    @Bean
    public SimpleJobLauncher jobLauncherRepository(JobRepository jobRepository) {
        SimpleJobLauncher jobLauncherRepository = new SimpleJobLauncher();
        jobLauncherRepository.setJobRepository(jobRepository);
        return jobLauncherRepository;
    }

    @Bean
    public Job exportUserJob(Step myStep) {

        return jobBuilderFactory.get("exportUserJob")
                .incrementer(new RunIdIncrementer())
                .flow(myStep)
                .end()
                .build();
    }


    @Bean
    public Step myStep(RepositoryItemReader<Pret> myReader, ItemProcessor<Pret, MimeMessage> myProcessor, ItemWriter<MimeMessage> myWriter) {
        return stepBuilderFactory.get("myStep")
                .<Pret, MimeMessage>chunk(5)
                .reader(myReader)
                .processor(myProcessor)
                .writer(myWriter)
                .build();
    }


    public DataSource getDataSource() {
        DataSourceBuilder dataSourceBuilder = DataSourceBuilder.create();
        dataSourceBuilder.driverClassName("com.mysql.cj.jdbc.Driver");
        dataSourceBuilder.url("jdbc:mysql://localhost:3306/p7-microservice?serverTimezone=UTC&useLegacyDatetimeCode=false");
        dataSourceBuilder.username("root");
        dataSourceBuilder.password("7513");
        return dataSourceBuilder.build();
    }


    @Bean
    public RepositoryItemReader<Pret> myReader(PretDao pretDao) {

        Map<String, Direction> sortMap = new HashMap<>();
        sortMap.put("id", Direction.DESC);

        return new RepositoryItemReaderBuilder<Pret>()
                .repository(pretDao)
                .methodName("findPretByStatus")
                .arguments(Arrays.asList(1L, 2L))
                .sorts(sortMap)
                .saveState(false)
                .build();
    }


    @Bean
    public PretFilterProcessorMail myProcessor() {
        return new PretFilterProcessorMail();
    }

    @Bean
    public PretItemWriterMail myWriter() {
        return new PretItemWriterMail();
    }

    @Bean
    public JavaMailSender getJavaMailSender() {
        JavaMailSenderImpl mailSender = new JavaMailSenderImpl();
        mailSender.setHost("smtp.gmail.com");
        mailSender.setPort(587);
        mailSender.setUsername("p7biblioadm@gmail.com");
        mailSender.setPassword("p7biblioadm12345");

        Properties mailProperties = mailSender.getJavaMailProperties();
        mailProperties.put("mail.smtp.starttls.enable", "true");
        mailProperties.put("mail.smtp.auth", "true");
        mailProperties.put("mail.transport.protocol", "smtp");
        mailProperties.put("mail.debug", "true");

        return mailSender;
    }


}